const mapContainer = document.querySelector('#map')
const mapOption = { // 지도 생성 시 필요한 기본 옵션
    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
    level: 5 // 지도의 확대, 축소 레벨
}

// 지도를 미리 생성
const map = new daum.maps.Map(mapContainer, mapOption)
// 주소-좌표 변환 객체를 생성
const geocoder = new daum.maps.services.Geocoder()
// 마커를 미리 생성
const marker = new daum.maps.Marker({
    position: new daum.maps.LatLng(37.537187, 127.005476),
    map: map
})

const searchedAdd = () => {
    new daum.Postcode({
        oncomplete: function(data) {
    		// 검색한 주소 값을 <input>에 설정
            const addr = data.address // 최종 주소 변수
            document.querySelector('#inputAdd').value = addr
            
            // 주소로 상세 정보를 검색
            geocoder.addressSearch(data.address, function(results, status) {
                if (status === daum.maps.services.Status.OK) {
                    const result = results[0] // 첫번째 결과의 값을 활용
                    const coords = new daum.maps.LatLng(result.y, result.x); // 해당 주소 좌표 받기
                    mapContainer.style.display = "block"
                    map.relayout()
                    map.setCenter(coords) // 지도 중심 변경
                    marker.setPosition(coords) // 마커를 주소 위치로 변경
                }
            })
        }
    }).open()
}
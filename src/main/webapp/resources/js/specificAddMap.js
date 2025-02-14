const mapContainer = document.querySelector('#map')
const mapOptions = { // 지도 생성 시 필요한 기본 옵션
	center: new daum.maps.LatLng(37.579225, 126.9368), // 특정 주소 좌표로 변경
	level: 5 // 지도의 확대, 축소 레벨
}

// 지도 생성 및 객체 리턴
const map = new daum.maps.Map(mapContainer, mapOptions)
// 마커 생성 및 객체 리턴
const marker = new daum.maps.Marker({ 
    position: new daum.maps.LatLng(37.579225, 126.9368),
    map: map
})
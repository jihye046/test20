const mapContainer = document.querySelector('#map')
const startAddressInput = document.querySelector("#startAddressInput")
const goalAddressInput = document.querySelector("#goalAddressInput")
const startAddressBtn = document.querySelector("#startAddressBtn")
const goalAddressBtn = document.querySelector("#goalAddressBtn")
const navigateBtn = document.querySelector("#navigateBtn")
let startAddress = ''
let goalAddress = ''
const geocoder = new daum.maps.services.Geocoder()

startAddressBtn.addEventListener('click', function(){
  new daum.Postcode({
    oncomplete: function(data) {
      const addr = data.address
      startAddressInput.value = addr
            
      // 주소로 상세 정보를 검색
      geocoder.addressSearch(data.address, function(results, status) {
        if (status === daum.maps.services.Status.OK) {
          const result = results[0]; // 첫번째 결과의 값을 활용
          const coordinates = new daum.maps.LatLng(result.x, result.y) // 해당 주소 좌표 받기
          startAddress = coordinates.toString()
        }
      })
    }
  }).open()
})
goalAddressBtn.addEventListener('click', function(){
  new daum.Postcode({
    oncomplete: function(data) {
      const addr = data.address
      goalAddressInput.value = addr
            
      // 주소로 상세 정보를 검색
      geocoder.addressSearch(data.address, function(results, status) {
        if (status === daum.maps.services.Status.OK) {
          const result = results[0]; // 첫번째 결과의 값을 활용
          const coordinates = new daum.maps.LatLng(result.x, result.y) // 해당 주소 좌표 받기
          goalAddress = coordinates.toString()
        }
      })
    }
  }).open()
})
navigateBtn.addEventListener('click', function(){
  if(startAddress && goalAddress){
  	// 좌표객체 사이 공백 제거
  	startAddress = startAddress.replace(/\s/g,'')
    goalAddress = goalAddress.replace(/\s/g,'')
    const requestData = {
      startAddress: startAddress,
      goalAddress: goalAddress
    }
    const xhr = new XMLHttpRequest()
    xhr.open('POST', '/map/findRoute', true) // true: 비동기, false: 동기
    xhr.setRequestHeader('Content-Type', 'application/json')
    xhr.onreadystatechange = function(){
      if(xhr.readyState === XMLHttpRequest.DONE){
        if(xhr.status === 200){
          console.log("findRoute success")
          console.log(xhr.responseText)
        } else {
          console.error("findRoute fail")
        }
      }
    }
	console.log(`start:${startAddress}`)
	console.log(`goal:${goalAddress}`)
    xhr.send(JSON.stringify(requestData))
  } else {
  	alert("출발지와 목적지 주소를 입력해주세요")
  }
})

/* 
$.ajax({
  type: 'GET',
  url: '/map/findRoute',
  data: {startAddress: startAddress, goalAddress: goalAddress},
  success: function(data){
    console.log("길찾기 안내 성공")
  },
  error: function(error){
    console.error("error msg: ", error)
  }
})
*/
const currentLocationWeather = document.querySelector(".currentLocationWeather")
const currentLocationWeeklyWeather = document.querySelector(".currentLocationWeeklyWeather")
const sessionLatitude = document.querySelector("#latitude")
const sessionLongitude = document.querySelector("#longitude")
let sessionLatitudeValue = sessionLatitude ? parseFloat(sessionLatitude.getAttribute("data-latitude")) : null
let sessionLongitudeValue = sessionLongitude ? parseFloat(sessionLongitude.getAttribute("data-longitude")) : null

// 현위치 정보 가져오기
document.querySelector("#currentLocationBtn").addEventListener('click', function(){
  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition((position) => {
      const locationObj = weatherLocation(position)
      getCurrentWeather(locationObj.latitude, locationObj.longitude)
    })
  } else {
    console.log("현재 위치 사용 불가능")
  }
})

// 위도, 경도 가져오기
const weatherLocation = (position) => {
  const locationObj = {
    latitude: position.coords.latitude,
    longitude: position.coords.longitude
  }
  return locationObj
}

// 세션에 저장된 현위치 위도, 경도가 없는 경우 기본(서울) 위도, 경도
const weatherDefaultLocation = () => {
  const SEOUL_LATITUDE = 37.5665
  const SEOUL_LONGITUDE = 126.9780
  const defaultLocationObj = {
    latitude: SEOUL_LATITUDE,
    longitude: SEOUL_LONGITUDE
  }
  return defaultLocationObj
}

// 서버에서 날씨 정보 가져오기
const getCurrentWeather = (latitude, longitude) => {
  // 세션에 저장된 위도, 경도가 없는 경우 서울로 지정
  if (!(latitude) || !(longitude)) {
    const defaultLocation  = weatherDefaultLocation()
    latitude = defaultLocation.latitude;
    longitude = defaultLocation.longitude;
  }

  // 오늘 날씨
	$.ajax({
    type: "get",
    url: "/category/getCurrentWeather",
    data: {latitude, longitude},
    success: function(currentWeatherDto){
      let output = updateCurrentWeatherInfo(currentWeatherDto)
      currentLocationWeather.innerHTML = output
    },
    error: function(error){
      console.error("날씨 정보 가져오기 실패", error)
    }
  })

  // 일주일 날씨
  $.ajax({
    type: "get",
    url: "/category/getWeeklyWeather",
    data: {latitude, longitude},
    success: function(weeklyWeatherDto){
      //let output = updateWeeklyWeatherInfo(weeklyWeatherDto)
      //currentLocationWeeklyWeather.innerHTML = output
      updateWeeklyWeatherInfo(weeklyWeatherDto)
    },
    error: function(){
      console.error("일주일 날씨 정보 가져오기 실패", error)
    }
  })
}

// 오늘 날씨 정보 화면에 업데이트
const updateCurrentWeatherInfo = (currentWeatherDto) => {
  const KELVIN_OFFSET = 273.15
  const main = currentWeatherDto.weather[0].main // 주요 날씨
  //const description = weatherDto.weather[0].description // 주요 날씨 상세
  const feels_like = (currentWeatherDto.main.feels_like - KELVIN_OFFSET).toFixed(1) // 체감온도
  const temp = (currentWeatherDto.main.temp - KELVIN_OFFSET).toFixed(1)
  const temp_max = (currentWeatherDto.main.temp_max - KELVIN_OFFSET).toFixed(1)
  const temp_min = (currentWeatherDto.main.temp_min - KELVIN_OFFSET).toFixed(1)
  
  let icon = ''
  let weather = ''
  switch(main){
    case 'Clear':
      icon = '<i class="bi bi-brightness-high"></i>'
      weather = '맑음'
      break;
    case 'Wind':
      icon = '<i class="bi bi-wind"></i>'
      weather = '바람'
      break;
    case 'Clouds':
      icon = '<i class="bi bi-clouds"></i>'
      weather = '흐림'
      break;
    case 'Rain':
      icon = '<i class="bi bi-cloud-rain-fill"></i>'
      weather = '비'
      break;
    case 'Snow':
      icon = '<i class="bi bi-snow2"></i>'
      weather = '눈'
      break;
    default:
      icon = '<p>icon err</p>'
  }

  let output = 
    `
        <p>${icon}</p>
        <p><h2>${temp}°</h2></p>
        <p>${weather}</p>
        <p>체감 ${feels_like}</p>
        <p><span id="temp_min">${temp_min}</span> / <span id="temp_max">${temp_max}</span></p>
    `
  return output
}

// dt_txt 문자열 분해
const parseDateTime = (dt_txt) => {
  // 날짜, 시간 분리
  const [dataPart, timePart] = dt_txt.split(" ") // 2001-01-01 21:00:00

  // 날짜 분해
  const [year, month, day] = dataPart.split("-")
  
  // 시간 분해
  const [hour, minute, second] = timePart.split(":")
  
  return {year, month, day, hour}
}

// 일주일 날씨 정보 화면에 업데이트
const updateWeeklyWeatherInfo = (weeklyWeatherDto) => {
  const KELVIN_OFFSET = 273.15
  const main = weeklyWeatherDto.list[0].weather[0].main // 주요 날씨
  const feels_like = (weeklyWeatherDto.list[0].main.feels_like - KELVIN_OFFSET).toFixed(1) // 체감온도
  const temp = (weeklyWeatherDto.list[0].main.temp - KELVIN_OFFSET).toFixed(1)
  const temp_max = (weeklyWeatherDto.list[0].main.temp_max - KELVIN_OFFSET).toFixed(1)
  const temp_min = (weeklyWeatherDto.list[0].main.temp_min - KELVIN_OFFSET).toFixed(1)
  const dt_txt = (weeklyWeatherDto.list[0].dt_txt)
  let dateTime = parseDateTime(dt_txt)
  //console.log(`hour:${dateTime.hour}`)

  // 하루 최대 8개의 dateTime 발생, 시간 지나면 순서대로 없어짐(-> 8개가 고정이 아님)
  
  let icon = ''
  let weather = ''
  switch(main){
    case 'Clear':
      icon = '<i class="bi bi-brightness-high"></i>'
      weather = '맑음'
      break;
    case 'Wind':
      icon = '<i class="bi bi-wind"></i>'
      weather = '바람'
      break;
    case 'Clouds':
      icon = '<i class="bi bi-clouds"></i>'
      weather = '흐림'
      break;
    case 'Rain':
      icon = '<i class="bi bi-cloud-rain-fill"></i>'
      weather = '비'
      break;
    case 'Snow':
      icon = '<i class="bi bi-snow2"></i>'
      weather = '눈'
      break;
    default:
      icon = '<p>icon err</p>'
  }

  let output = 
    `
        <p>${icon}</p>
        <p><h2>${temp}°</h2></p>
        <p>${weather}</p>
        <p>체감 ${feels_like}</p>
        <p>
          <span id="temp_min">${temp_min}</span> / <span id="temp_max">${temp_max}</span>
        </p>
    `
  return output
  
}

// 페이지 로드 시 함수 호출
getCurrentWeather(sessionLatitudeValue, sessionLongitudeValue)
const weatherMain = document.querySelector("#weather-main")
const weatherDescription = document.querySelector("#weather-description")
const weatherIcon = document.querySelector("#weather-icon")
const weatherFeelsLike = document.querySelector("#weather-feels-like")
const weatherTemp = document.querySelector("#weather-temp")
const weatherTempMax = document.querySelector("#weather-temp-max")
const weatherTempMin = document.querySelector("#weather-temp-min")

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

// 서버에서 날씨 정보 가져오기
const getCurrentWeather = (latitude, longitude) => {
	$.ajax({
    type: "get",
    url: "/category/getCurrentWeather",
    data: {latitude,longitude},
    success: function(weatherDto){
      updateWeatherInfo(weatherDto)
    },
    error: function(error){
      console.error("날씨 정보 가져오기 실패", error)
    }
  })
}

// 날씨 정보 화면에 업데이트
const updateWeatherInfo = (weatherDto) => {
  const KELVIN_OFFSET = 273.15
  const main = weatherDto.weather[0].main // 주요 날씨
  const description = weatherDto.weather[0].description // 주요 날씨 상세
  const icon = weatherDto.weather[0].icon
  const feels_like = (weatherDto.main.feels_like - KELVIN_OFFSET).toFixed(1) // 체감온도
  const temp = (weatherDto.main.temp - KELVIN_OFFSET).toFixed(1)
  const temp_max = (weatherDto.main.temp_max - KELVIN_OFFSET).toFixed(1)
  const temp_min = (weatherDto.main.temp_min - KELVIN_OFFSET).toFixed(1)

  weatherMain.innerHTML = `주요날씨: ${main}<br>`
  weatherDescription.innerHTML = `주요날씨상세: ${description}<br>`
  weatherIcon.innerHTML = `날씨아이콘: ${icon}<br>`
  weatherFeelsLike.innerHTML = `체감기온: ${feels_like}<br>`
  weatherTemp.innerHTML = `현재기온: ${temp}<br>`
  weatherTempMax.innerHTML = `최고기온: ${temp_max}<br>`
  weatherTempMin.innerHTML = `최저기온: ${temp_min}<br>`
}


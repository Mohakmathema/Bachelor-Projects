const timeEl=document.querySelector(".time")
const dateEl =document.querySelector(".date")


const days=['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thrusday', 'Friday', 'Saturday'];
const months =['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

setInterval(() =>{
    const time = new Date();
    const month = time.getMonth();
    const date = time.getDate();
    const day = time.getDay();
    const hour = time.getHours();
    const hoursIn12Hrs = hour >= 13? hour % 12: hour
    const minutes = time.getMinutes();
    const ampm = hour >= 12? 'PM' : 'AM'

    timeEl.innerHTML = hoursIn12Hrs + ':' + minutes+ ' ' + `<span id="ampm">${ampm}</span>`

    dateEl.innerHTML = days[day] + ',' +date+ ' '+months[month]

},1000)

let weather = {
    api_Key : "9d7e0a1912236f8fbd10638bf5df6a55",
    fetchWeather: function (city) {
        fetch("https://api.openweathermap.org/data/2.5/weather?q="
            + city
            + "&units=metric&appid="
            + this.api_Key)
            .then((response)=>response.json())
            .then((data)=>this.displayWeather(data))
            .catch((error)=> document.querySelector(".location").innerText = "City not found");

    },
    displayWeather: function (data){
        const { name } = data;
        const { icon, description } = data.weather[0];
        const { temp, humidity, pressure } = data.main;
        const { speed } = data.wind;
        console.log(name,icon,description,temp,humidity,speed);
        document.querySelector(".location").innerText = "Weather in "
            + name;
        document.querySelector(".icon").src = "https://openweathermap.org/img/wn/"+ icon +"@4x.png";
        document.querySelector(".description").innerText = description;
        document.querySelector(".today").innerText = temp + "°C";
        document.querySelector(".humid").innerText = humidity + " %";
        document.querySelector(".wind_speed").innerText = speed + " km/h";
        document.querySelector(".pressur").innerText = pressure + "hPa";
    },
    search: function (){
        this.fetchWeather(document.querySelector(".search-bar").value);
    },
};

document.querySelector(".search button").addEventListener("click", function (){
    weather.search();
});

document.querySelector(".search-bar").addEventListener("keyup", function (event){
    if (event.key == "Enter"){
        weather.search();
    }
})

weather.fetchWeather("Glasgow");
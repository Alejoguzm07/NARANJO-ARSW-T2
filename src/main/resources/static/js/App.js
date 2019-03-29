var module = (function () {
    var showInfo = function (weather) {
        $("#info").append('<li> lon: '+weather.lon+'</li>');
        $("#info").append('<li> lat: '+weather.lat+'</li>');
        $("#info").append('<li> weather: '+weather.weather+'</li>');
        $("#info").append('<li> description: '+weather.description+'</li>');
        $("#info").append('<li> temperature: '+weather.temperature+'</li>');
        /*$("#info").append('<li> pressure: '+weather.pressure+'</li>');
        $("#info").append('<li> humidity: '+weather.humidity+'</li>');
        $("#info").append('<li> city: '+weather.city+'</li>');*/
    };
    return {
      updateInfo:function (){
        $('#info').empty();
        $('#city').val();
        var cityName = $('#city').val();      
        if(cityName != ''){
          apiclient.getWeatherByCity(cityName,showInfo);
        };      
      }
    };
  })();
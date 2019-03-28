apiclient=(function(){
    return {
		getWeatherByCity: function(name,callback){            
			$.get( "/weather/"+name, function( data ) {
                callback(data);
              });
		}
	}
})();
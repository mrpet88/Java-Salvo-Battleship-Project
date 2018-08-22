var main = new Vue({
    el: '#VueMain',
    data: {
        
        leaderboard: [],
        ascendanceArray:[],
        names:[
            {"name":"marinos"},
            {"name":"chris"},
            {"name":"angel"},
            {"name":"michael"},
            {"name":"anna"}
        ]
        
    },
    methods: {
        
    descendance: function() {
    for (var i=0; i<this.names.length; i++){
         this.names.sort(function(a,b){return a - b});
         return this.names;
    }
   
    console.log(this.names);
    },
 
    },
    
});

start();



function start() {

    console.log(1);
    var fetchConfig =
        fetch("/api/leaderboard/" , {
            method: "GET",
            credentials: "include",
        })
        /*,
			headers: new Headers({
				"API-whatever-you-want": 'SDHFAIWEFN4r34SEHFE4534sdsDF'
			})
			*/

        .then(onDataFetched)
        .catch(onDataFetchFailed);

    console.log(3);
}

function onDataFetched(response) {
    response.json()
        .then(onConversionToJsonSuccessful)
        .catch(onConversionToJsonFailed);
    console.log(2);
}

function onDataFetchFailed(error) {
    console.log("I have failed in life.", error);
}

function onConversionToJsonSuccessful(json) {
    console.log("success!!!!", json);
    data = json;
    main.leaderboard = data.sort((a,b) => b.totalScore - a.totalScore);
    console.log(data)


    

}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

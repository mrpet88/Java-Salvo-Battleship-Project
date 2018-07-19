var main = new Vue({
    el: '#VueMain',
    data: {
        games: [],
        dates: [],
    },
    methods: {
        date: function () {
            var onlyDates = [];
            
            for (var i = 0; i < this.games.length; i++) {
                var eachListObject= { 
                    timestamp: new Date(this.games[i].created),
                    players: new Date(this.games[i].gamePlayers),
                    timestamp: new Date(this.games[i].created),
                    
                
                }
                timestamp = new Date(this.games[i].created)
                onlyDates.push(timestamp)
//                console.log(onlyDates);
            }
            main.dates = onlyDates;
        }
    }
});

start();
console.log("outside");


function start() {
    console.log(1);

    var fetchConfig =
        fetch("/api/games", {
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
    //    
    //    console.log(data);
    main.games = data;
    main.date()
    //    main.teams = data.teams;


}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

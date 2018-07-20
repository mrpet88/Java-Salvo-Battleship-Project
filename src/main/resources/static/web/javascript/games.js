var main = new Vue({
    el: '#VueMain',
    data: {
        games: [],
        listObjects:[],
    },
    methods: {
        makeObject: function () {
            var listObject = [];
            for (var i = 0; i < this.games.length; i++) {
                var date = new Date(this.games[i].created).toLocaleString()
                var playerOne = this.games[i].gamePlayers["0"].player.userName;
                if (this.games[i].gamePlayers.length > 1) {
                    var playerTwo = this.games[i].gamePlayers["1"].player.userName;
                }
                else {
                    var playerTwo = 'JOIN';
                }
                listObject[i] = {
                    'date': date,
                    'playerOne': playerOne,
                    'playerTwo': playerTwo,
                }
            }
            this.listObjects=listObject;
            console.log(this.listObjects);
        }
    }
});

start();

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
    main.games = data;
    main.makeObject()
}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

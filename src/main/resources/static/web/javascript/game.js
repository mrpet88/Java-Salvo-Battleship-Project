var main = new Vue({
    el: '#VueMain',
    data: {
        game_view: [],
        rowNumbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        colLetters: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", ],
        id: '',
        playerOne: [],
        playerTwo: [],
        shipUserLocations: [],
        salvoEnemyLocations: [],
        userHits: [],
        enemyHits: [],
        logOutButton: true,
        player: [],


    },
    methods: {
        historyBackButton:function(){
            window.history.back();        
        },
        getGames: function () {
            fetch("/api/games", {
                    method: "GET",
                    credentials: "include",
                })
                .then(response => response.json())
                .then(response => {
                    console.log(response.player);
                    console.log(response.player != null)
                    if (response.player != null) {

                        main.logOutButton = true;
                    }
                })
                .catch(response => console.log(response));
        },

        logOut: function () {
            fetch("/api/logout", {
                credentials: 'include',
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
            })
            location.reload();

        },
        userShipPosition: function () {
            let array = [];
            for (var i = 0; i < this.game_view.ships.length; i++) {
                for (var j = 0; j < this.game_view.ships[i].location.length; j++) {
                    var shipLocation = this.game_view.ships[i].location[j];
                    document.getElementById("U" + shipLocation).classList.add("ship-location");
                    array.push(shipLocation)
                }
            }
            this.shipUserLocations = array;
        },
        salvoUserPosition: function () {
            var userHits = [];
            for (var i = 0; i < this.game_view.UserSalvos.length; i++) {
                {
                    let turn = this.game_view.UserSalvos[i].turn;

                    for (var k = 0; k < this.game_view.UserSalvos[i].location.length; k++) {
                        var salvoLocation = this.game_view.UserSalvos[i].location[k];
                        userHits.push(salvoLocation);
                        document.getElementById("E" + salvoLocation).classList.add("salvo-location");
                        document.getElementById("E" + salvoLocation).innerHTML = turn
                    }
                    this.userHits = userHits;
                }
            }
        },
        salvoEnemyPosition: function () {
            var enemySalvos = [];
            for (var i = 0; i < this.game_view.EnemySalvos.length; i++) {
                let turn = this.game_view.EnemySalvos[i].turn;
                for (var k = 0; k < this.game_view.EnemySalvos[i].location.length; k++) {
                    var salvoLocation = this.game_view.EnemySalvos[i].location[k];
                    enemySalvos.push(salvoLocation);
                    document.getElementById("U" + salvoLocation).classList.add("salvo-location");
                    document.getElementById("U" + salvoLocation).innerHTML = turn
                }
                this.salvoEnemyLocations = enemySalvos;

            }
        },
        shipIsHit: function (ship, salvo, letter) {
            var arrayThird = [];
            for (var i = 0; i < salvo.length; i++) {
                for (var j = 0; j < ship.length; j++) {
                    if (salvo[i] == ship[j]) {
                        arrayThird.push((salvo[i]))
                        var hit = salvo[i]
                        document.getElementById(letter + hit).classList.add("hit");
                        document.getElementById(letter + hit).classList.remove("salvo-location");
                        document.getElementById(letter + hit).classList.remove("ship-location");
                    }
                }
            }
            this.enemyHits = arrayThird;

        },
        findTheId: function () {
            var url = location.search;
            var x = url.split('=')[1];
            this.id = x;
            start(this.id);
        },
        getTheName: function () {
            for (var i = 0; i < this.game_view.game.gamePlayers.length; i++) {
                if (this.id == this.game_view.game.gamePlayers[i].id) {
                    var playerOne = (this.game_view.game.gamePlayers[i].player.userName);
                } else {
                    var playerTwo = (this.game_view.game.gamePlayers[i].player.userName);

                }
            }
            this.playerOne = playerOne + " " + "(you)";
            this.playerTwo = playerTwo;
        },
        returnButton: function(){
                if (this.id == this.game_view.game.gamePlayers[i].id)     
                console.log(location.href)
            
        }
    },
});

main.findTheId();

function start(id) {

    console.log(1);
    var fetchConfig =
        fetch("/api/game_view/" + id, {
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
    data = json["game-view"];
    main.game_view = data;
    main.userShipPosition()
    main.getTheName()
    main.salvoUserPosition()
    main.salvoEnemyPosition()
    main.shipIsHit(main.shipUserLocations, main.salvoEnemyLocations, "U");

}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

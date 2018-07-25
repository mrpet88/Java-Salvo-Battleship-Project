var main = new Vue({
    el: '#VueMain',
    data: {
        game_view: [],
        rowNumbers:[1,2,3,4,5,6,7,8,9,10],
        colLetters:["A","B","C","D","E","F","G","H","I","J",],
        id:'',
        playerOne:[],
        playerTwo:[],
        players:[],
        
    },
    methods: {
        makeObject: function () {
            for (var i = 0; i < this.game_view.ships.length; i++){
            for (var j=0; j<this.game_view.ships[i].location.length; j++)
            {
                var shipLocation = this.game_view.ships[i].location[j];
                console.log(shipLocation);
                document.getElementById(shipLocation).classList.add("ship-location");   
            }
            }
        },
//        .salvos["0"]["0"].location
        makeSalvoObject: function () {
            for (var i = 0; i < this.game_view.salvos[i].length; i++){
                console.log(this.game_view.salvos[i].length);
            for (var j=0; j<this.game_view.salvos[i][i].location.length; j++)
                console.log(this.game_view.salvos[i][i].location.length);

            {
                var salvoLocation = this.game_view.salvos[i][i].location[j];
                console.log(salvoLocation);
                document.getElementById(shipLocation).classList.add("ship-location");   
            }
            }
        },
        findTheId: function(){
            var url = location.search;
            var x = url.split('=')[1];
            this.id=x;
            start(this.id);

        },
        getTheName: function(){
            var players=[];
            for(var i=0; i<this.game_view.game.gamePlayers.length; i++){
                if (this.id==this.game_view.game.gamePlayers[i].id ){
                var playerOne = (this.game_view.game.gamePlayers[i].player.userName);
                    }
                else{
                var playerTwo = (this.game_view.game.gamePlayers[i].player.userName);

                }
            }
            this.playerOne=playerOne+" "+"(you)";
            this.playerTwo=playerTwo;
        }
    },
});

main.findTheId();
function start(id) {
    
    console.log(1);
    var fetchConfig =
        fetch("/api/game_view/" +id, {
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
    main.game_view = data;
    main.makeObject()
    main.getTheName()
    main.makeSalvoObject()
 
}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}


      

var clas = "";
var aircraftCarrier = [];
var battleship = [];
var destroyer = [];
var patrolboat = [];
var submarine = [];
var rowNumbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
var colLetters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
var allShipLocations = [];
var classOfShip = '';


//function rotate(imgId){
//    
////    img.removeAttribute('style','transform:rotate(90deg)');
//    var img=document.getElementById(imgId);
//    if (img.getAttribute==('style','transform:rotate(90deg)')){
//        console.log("image get")
//    img.removeAttribute('style','transform:rotate(90deg)');
//}
//    else{
//    img.setAttribute('style','transform:rotate(90deg)');
//}}

function rotate(imgId) {

    if ($("#" + imgId).hasClass("horizontal")) {
        $("#" + imgId).removeClass("horizontal").addClass("vertical")
        classOfShip = "horizontal"
        console.log(classOfShip);

    } else {
        $("#" + imgId).removeClass("vertical").addClass("horizontal")
        classOfShip = "vertical"
        console.log(classOfShip);



    }

}





function allowDrop(ev) {
    ev.preventDefault();
    console.log("allow drop");
}


function drag(ev) {
    //    var id = $(ev.target).attr('id');
    clas = $(ev.target).attr('class');
    console.log(" / class: " + clas);
    ev.dataTransfer.setData("text", ev.target.id);
    if (clas == "aircraftCarrier") {
        aircraftCarrier = [];
    } else if (clas == "battleship") {
        battleship = [];
    } else if (clas == "destroyer") {
        destroyer = [];
    } else if (clas == "patrolboat") {
        patrolboat = [];
    } else if (clas == "submarine") {
        submarine = [];
    }
    console.log("dragging")

}

function findNumber(arr, k) {
    for (var i = 0; i < arr.length; i++) {
        if (arr.includes(k[i])) {
            console.log("YES");
        } else {
            console.log("NO");
        }
    }

}


//findNumber(array, 1)
//findNumber(array2, 5)



function drop(ev) {
    console.log(ev)
    console.log(ev.toElement.classList.contains("cell"), !ev.toElement.classList.contains("ship-located"))
    if (ev.toElement.classList.contains("cell") && !ev.toElement.classList.contains("ship-located")) {

        var _target = $("#" + ev.target.id);
        $("#" + ev.target.id).addClass("ship-located")
        console.log(ev.target.id);
        if ($(_target).hasClass("noDrop")) {
            ev.preventDefault();
            alert("Wrong Position")
        } else {

            ev.preventDefault();
            var data = ev.dataTransfer.getData("text");
            ev.target.appendChild(document.getElementById(data));
            console.log("on drop");
            var letterNumberArray = ev.target.id.split(/(?=[1-9])/);
            console.log(letterNumberArray);
            var elementLetter = letterNumberArray[0]
            //            console.log(elementLetter)
            var elementLetterSliced = elementLetter.slice(1);
            console.log(elementLetterSliced);

            var elementNumber = parseInt(letterNumberArray[1]);
            console.log(elementNumber)


            var locations = [];


            if ((classOfShip == "") || (classOfShip == "horizontal")) {

                if ((clas == "aircraftCarrier") && (elementNumber <= 7)) {
                    for (var i = 0; i < 5; i++) {
                        var x = elementNumber;

                        locations.push(elementLetterSliced + elementNumber)
                        console.log("locations:" + locations)
                        aircraftCarrier = locations
                        console.log(battleship)
                        elementNumber = x + 1;

                    }
                } else if ((clas == "battleship") && (elementNumber <= 7)) {
                    for (var j = 0; j < 4; j++) {

                        var x = elementNumber;
                        locations.push(elementLetterSliced + elementNumber)
                        console.log("locations:" + locations)
                        battleship = locations
                        console.log(aircraftCarrier)

                        elementNumber = x + 1;
                    }
                } else if ((clas == "patrolboat") && (elementNumber <= 7)) {
                    for (var j = 0; j < 2; j++) {

                        var x = elementNumber;
                        locations.push(elementLetterSliced + elementNumber)
                        console.log("locations:" + locations)
                        patrolboat = locations
                        console.log(patrolboat)

                        elementNumber = x + 1;
                    }
                } else if ((clas == "destroyer") && (elementNumber <= 7)) {
                    for (var j = 0; j < 3; j++) {

                        var x = elementNumber;
                        locations.push(elementLetterSliced + elementNumber)
                        console.log("locations:" + locations)
                        destroyer = locations
                        console.log(destroyer)

                        elementNumber = x + 1;
                    }
                } else if ((clas == "submarine") && (elementNumber <= 7)) {
                    for (var j = 0; j < 3; j++) {

                        var x = elementNumber;
                        locations.push(elementLetterSliced + elementNumber)
                        console.log("locations:" + locations)
                        submarine = locations
                        console.log(submarine)

                        elementNumber = x + 1;
                    }
                } else {
                    alert("Please place the ship inside the grid")
                }
            } else if (classOfShip == "vertical") {
                if ((clas == "aircraftCarrier") && (elementLetter <= 7)) {
                    for (var i = 0; i < 5; i++) {
                        var x = elementNumber;

                        locations.push(elementLetterSliced + elementNumber)
                        console.log("locations:" + locations)
                        aircraftCarrier = locations
                        console.log(battleship)
                        elementNumber = x + 1;

                    }
                }


            }
            var allLocations = aircraftCarrier.concat(battleship).concat(destroyer).concat(patrolboat).concat(submarine);
            allShipLocations = allLocations;
            console.log(allShipLocations);
        }
    }
}


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
        secondPlayerGrid: false,
        shipsObject: [],

    },
    created: function () {
        this.findTheId();
        start(this.id);
        //        this.drag();
        //        this.dragstart();
        //        this.dragend();
        //        this.dragover();
        //        this.dragenter();
    },
    methods: {

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
        listOfShips: function (id) {
            fetch("/api/games/players/" + id + "/ships", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify()
                })
                .then(response => {
                    response.json().then(function (response) {
                        console.log(response);
                        console.log(id)

                    })
                    this.secondPlayerGrid = true;


                })

                .catch(function (e) {
                    console.log(e)

                })
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
            this.id = x
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
        returnButton: function () {
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

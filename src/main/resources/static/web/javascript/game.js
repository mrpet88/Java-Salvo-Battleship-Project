var arrayOfShips = [];
var tableLetters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
var tableNumbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
var newShip = [];

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    if (ev.toElement.classList.contains("cell") && !ev.toElement.classList.contains("ship-located")) {
        var _target = $("#" + ev.target.id);
        $("#" + ev.target.id).addClass("ship-located")
        console.log("#" + ev.target.id)
        if ($(_target).hasClass("noDrop")) {
            ev.preventDefault();
        } else {
            ev.preventDefault();
            var data = ev.dataTransfer.getData("text");
            ev.target.appendChild(document.getElementById(data));
            fillArrayOfShips(ev.target.id)
        }
    }
}

function addlistener() {
    document.getElementById()
}

function fillArrayOfShips(id) {

    var cell = document.getElementById(id);
    var ship = cell.childNodes[0];

    var shipInformation = ship.getAttribute("class");
    console.log(shipInformation);
    var shipType = shipInformation.split(" ")[0];
    var shipDirection = shipInformation.split(" ")[1];


    var letterNumberArray = id.split(/(?=[1-9])/);

    var elementLetter = letterNumberArray[0];
    var slicedLetter = elementLetter.slice(1);

    var number = (letterNumberArray[1]);
    var elementNumber = parseInt(number);

    var shipSize;
    if (shipType == "aircraftCarrier") {
        shipSize = 4;
    }
    if (shipType == "battleship") {
        shipSize = 3;
    }
    if (shipType == "destroyer") {
        shipSize = 2;
    }
    if (shipType == "patrolboat") {
        shipSize = 2;
    }
    if (shipType == "submarine") {
        shipSize = 3;
    }

    var locations = [];
    locations.push(slicedLetter + elementNumber)
    if (shipDirection == "horizontal") {
        for (var i = 0; i < shipSize; i++) {
            elementNumber++;
            locations.push(slicedLetter + elementNumber)
        }
    } else {
        for (var i = 0; i < (shipSize + 1); i++) {
            slicedLetter = tableLetters[tableLetters.indexOf(slicedLetter) + 1]
            locations.push(slicedLetter + elementNumber)
        }
        locations.shift()
    }

    newShip = {
        type: shipType,
        locations: locations
    }

    for (var i = 0; i < arrayOfShips.length; i++) {
        if (arrayOfShips[i].type == newShip.type) {
            var index = arrayOfShips.indexOf(arrayOfShips[i]);
            console.log(index);
            if (index > -1) {
                arrayOfShips.splice(index, 1);
            }
        }
    }
    arrayOfShips.push(newShip);
    var error = compare(arrayOfShips)
    if (error) {
        placeShipBack(ship)
    }
    console.log(arrayOfShips)

    if (arrayOfShips.length == 5) {
        main.placeShips = true;

    }
}

function rotate(id) {
    const cell = document.getElementById(id);

    if (cell.classList.contains("horizontal")) {
        cell.classList.remove("horizontal");
        cell.classList.add("vertical")
        rotationClass = "vertical";

    } else {
        cell.classList.add("horizontal");
        cell.classList.remove("vertical")
        rotationClass = "horizontal";
    }
    console.log(rotationClass)
    fillArrayOfShips(cell.parentNode.id)
}
var allTheTableLocationsArray = [];

function fillTheGridLocations() {
    for (var i = 0; i < tableLetters.length; i++) {
        for (var j = 0; j < tableNumbers.length; j++) {
            allTheTableLocationsArray.push(tableLetters[i] + tableNumbers[j]);
        }
    }
    return allTheTableLocationsArray;
}


function compare(ships) {
    var possibleGridLocations = fillTheGridLocations();
    var arrayOfLocationsInUse = [];
    var error = false;
    for (var i = 0; i < ships.length; i++) {
        let ship = ships[i];
        for (let j = 0; j < ship.locations.length; j++) {
            let eachLocation = ship.locations[j];
            if (possibleGridLocations.includes(eachLocation)) {
                if (!arrayOfLocationsInUse.includes(eachLocation)) {
                    arrayOfLocationsInUse.push(eachLocation);
                } else {
                    var index = ships.indexOf(ship);
                    if (index > -1) {
                        ships.splice(index, 1);
                        console.log(ships.splice(index, 1));
                    }
                    error = true;
                }
            } else {
                var index = ships.indexOf(ship);
                if (index > -1) {
                    ships.splice(index, 1);
                    console.log(ships.splice(index, 1));
                }
                error = true;
            }
        }
    }
    return error;
}

function placeShipBack(ship) {
    document.getElementById("ships-container").appendChild(ship)
}

var main = new Vue({
    el: '#VueMain',
    data: {
        game_view: [],
        rowNumbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        colLetters: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
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
        placeShips: false,
        allTheShips: true,
        salvoLocations: [],
        salvosToPost: [],
        fireButton: false,
        salvoesCells: [],

    },
    created: function () {
        this.findTheId();
        start(this.id);

    },
    methods: {
        findTheSunkShips: function () {
            for (var i = 0; i < this.game_view.hits.length; i++) {
                var sunkLocations = "E" + this.game_view.hits[i]
                document.getElementById(sunkLocations).classList.remove("salvo-location")
                document.getElementById(sunkLocations).classList.add("sunk-location");

            }
        },
        findTheUserSunkShips: function () {
            for (var i = 0; i < this.game_view.ships.length; i++) {
                for (var j = 0; j < this.game_view.ships[i].location.length; j++) {
                    if (this.game_view.ships[i].sunk == true) {
                        var sunkUserLocations = "U" + this.game_view.ships[i].location[j]
                        document.getElementById(sunkUserLocations).classList.remove("salvo-location")
                        document.getElementById(sunkUserLocations).classList.remove("ship-location")
                        document.getElementById(sunkUserLocations).classList.add("sunk-user-location");
                    }
                }
            }
        },

        beforePlacingTheShips: function () {
            if (this.secondPlayerGrid == false) {
                var oneId = document.getElementById("r")
                oneId.classList.add("only-one-table")
                oneId.classList.remove("flex-row")
            } else if (this.secondPlayerGrid == true) {
                oneId.classList.add("flex-row")
                oneId.classList.remove("only-one-table")
            }
        },
        bothPlayersGrid: function () {
            for (var i = 0; i < this.game_view.ships.length; i++) {
                if (this.game_view.ships.length > 0) {
                    this.secondPlayerGrid = true
                    this.allTheShips = false
                }
            }
        },
        getSalvoId: function (salvoId) {
            if (!document.getElementById("E" + salvoId).classList.contains("salvo-location")) {
                if (!this.salvoesCells.includes(salvoId)) {
                    this.salvoesCells.push(salvoId);
                } else {
                    var index = this.salvoesCells.indexOf(salvoId);
                    if (index > -1) {
                        document.getElementById("E" + this.salvoesCells.splice(index, 1)).classList.remove('hit-location');
                    }
                }
                this.fillSalvoArray()
            }
        },
        fillSalvoArray: function () {
            var cells = this.salvoesCells;
            //            console.log(cells)
            console.log(this.salvoesCells)
            if (cells.length > 5) {
                document.getElementById("E" + cells.splice(0, 1)).classList.remove('hit-location');
            }
            for (var i = 0; i < cells.length; i++) {
                document.getElementById('E' + cells[i]).classList.add('hit-location');
            }
            if (cells.length == 5) {
                this.fireButton = true;
            } else {
                this.fireButton = false;
            }

            this.salvosToPost = cells
        },
        postTheShips: function () {
            main.listOfShips(arrayOfShips, main.id)
            alert("please reload the page")

        },
        postTheSalvoes: function () {
            main.listOfSalvoes(main.id)
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
        listOfShips: function (ship, id) {
            fetch("/api/games/players/" + id + "/ships", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(ship)
                })
                .then(response => {
                    response.json().then(function (response) {

                    })
                })

                .catch(function (e) {
                    console.log(e)

                })
        },
        listOfSalvoes: function (id) {
            console.log()
            fetch("/api/games/players/" + id + "/salvos", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.salvosToPost)
                })
                .then(response => {
                    console.log(response)
                    response.json().then(function (response) {

                    })
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
            console.log("in")
            for (var i = 0; i < this.game_view.UserSalvos.length; i++) {
                for (var j = 0; j < this.game_view.UserSalvos[i].location.length; j++) {
                    var salvoLocation = this.game_view.UserSalvos[i].location[j]
                    var cellId = "E" + salvoLocation;
                    document.getElementById(cellId).classList.add("salvo-location");
                }
            }
        },

        salvoUserPosition1: function () {
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
    //    console.log()
    if (data.ships.length == 5) {
        main.allTheShips = false;
        main.secondPlayerGrid = true;
    }
    //    setTimeout(function(){
    console.log(arrayOfShips);
    console.log(main.allTheShips)
    main.game_view = data;
    main.userShipPosition()
    main.getTheName()
    main.bothPlayersGrid();
    main.salvoEnemyPosition()
    main.salvoUserPosition1()
    main.findTheSunkShips();
    main.findTheUserSunkShips();
    //        main.shipIsHit(main.shipUserLocations, main.salvoEnemyLocations, "U");
    //        main.hideTheShips();
    for (var i = 0; i < main.game_view.UserSalvos.length; i++) {
        if (main.game_view.UserSalvos[i].location.length == 5) {
            console.log("hello")
            main.salvoUserPosition()
        }
    }
    main.beforePlacingTheShips();


    //    },1000)


}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

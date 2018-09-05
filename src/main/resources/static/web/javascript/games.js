var main = new Vue({
    el: '#VueMain',
    data: {
        games: [],
        leaderboard: [],
        logedUser: false,
        player: [],
        playerName: "",
        logInButton: true,
        signUpButton: true,
        logOutButton: false,
        playerOne: "",
        playerTwo: "",
        hidetable: false,
        notable: true,
        id: ""
    },
    created: function () {
        this.getGames()
        this.hideGameTable()
        //        this.joinAgame()

    },
    methods: {
        hideGameTable: function () {
            console.log(this.playerName)
            if (this.playerName == '') {
                this.hidetable = true;
                this.notable = false;
            } else {
                this.hidetable = false;
                this.notable = true;
            }
        },
        createGame: function () {
            fetch("/api/games", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                })
                .then(response => {
                    response.json().then(data => window.location.replace("/web/game.html?gp=" + data.gamePlayerCreated))
                })


                .catch(function (e) {
                    console.log(e)

                })
        },


        logIn: function () {
            var message = ""
            var userName = document.getElementById("username").value

            var password = document.getElementById("password").value
            console.log(password);
            fetch("/api/login", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'userName=' + userName + '&password=' + password,
                })
                .then(response => {
                    if (response.status == 200) {
                        location.reload()
                        main.getGames();
                        main.hideGameTable()
                    } else {
                        alert("please fill the right username and password")
                        //                        document.getElementById("message").innerHTML = "please fill the right username and password"
                    }

                })
                .catch(function (e) {
                    console.log(e)
                })
        },
        logOut: function () {
            var message = ""
            var userName = document.getElementById("username").value

            var password = document.getElementById("password").value
            console.log(password);
            fetch("/api/logout", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'userName=' + userName + '&password=' + password,
                })
                .then(response => {
                    if (response.status == 200) {
                        location.reload();
                        main.getGames();
                    }
                })
                .catch(function (e) {
                    console.log(e)
                })
        },
        getGames: function () {
            fetch("/api/games", {
                    method: "GET",
                    credentials: "include",
                })
                .then(response => response.json())
                .then(response => {

                    if (response.player != null) {
                        main.logedUser = true;
                        main.playerName = response.player.userName
                        main.logInButton = false;
                        main.logOutButton = true;
                        main.signUpButton = false;
                    }


                    console.log(response);
                    var numberOfGame = 0;
                    for (i = 0; i < response.games.length; i++) {
                        numberOfGame += 1
                        var eachGame = document.createElement("tr");
                        var tableData5 = document.createElement("td");
                        //                        main.gameId = response.games[i].id;
                        main.playerOne = {
                            userName: response.games[i].gamePlayers["0"].player.userName,
                            gameplayer: response.games[i].gamePlayers["0"].id
                        };
                        if (response.games[i].gamePlayers["1"] != null) {
                            main.playerTwo = {
                                userName: response.games[i].gamePlayers["1"].player.userName,
                                gameplayer: response.games[i].gamePlayers["1"].id
                            }
                        } else {
                            main.playerTwo = {
                                userName: "pending",
                                gameplayer: ""
                            }
                        }


                        var numberNode = document.createTextNode(numberOfGame)
                        var tableData1 = document.createElement("td");
                        var textnode1 = document.createTextNode(this.playerOne.userName);
                        var tableData2 = document.createElement("td");
                        var tableData4 = document.createElement("td");

                        if (main.playerOne.userName == main.playerName) {
                            tableData2.classList.add("loggedPlayer");
                            var button = document.createElement("a");
                            button.setAttribute("href", "/web/game.html?gp=" + main.playerOne.gameplayer)
                            button.classList.add("btn-outline-light");
                            button.classList.add("btn");
                            var buttonText = document.createTextNode("ENTER GAME");
                            button.appendChild(buttonText);
                            tableData5.appendChild(button);
                        } else if (main.playerTwo.userName == main.playerName) {
                            tableData4.classList.add("loggedPlayer");
                            var button = document.createElement("a");
                            button.setAttribute("href", "/web/game.html?gp=" + main.playerTwo.gameplayer)
                            button.classList.add("btn-outline-light");
                            button.classList.add("btn");
                            var buttonText = document.createTextNode("ENTER GAME");
                            button.appendChild(buttonText);
                            tableData5.appendChild(button);
                        } else if ((main.playerName != main.playerOne.userName) && (main.playerName != main.playerTwo.userName) && (main.playerTwo.userName != "pending")) {
                            var button = document.createElement("BUTTON");
                            button.classList.add("btn-outline-danger");
                            button.classList.add("btn");
                            var buttonText = document.createTextNode("NO ACCESS");
                            button.appendChild(buttonText);
                            tableData5.appendChild(button);
                        } else if (main.playerTwo.userName == "pending") {
                            var button = document.createElement("a");
                            button.classList.add("btn-outline-light");
                            button.classList.add("btn");
                            var buttonText = document.createTextNode("JOIN GAME");
                            button.appendChild(buttonText);
                            tableData5.appendChild(button);
                            button.setAttribute("data-gameId", response.games[i].id)

                            button.addEventListener("click", function () {
                                var gameId = this.getAttribute("data-gameId");
                                console.log(gameId)
                                main.joinAgame(gameId)
                            })
                        }

                        var tableData3 = document.createElement("td");
                        var textnode3 = document.createTextNode("vs");
                        var textnode2 = document.createTextNode(this.playerTwo.userName);
                        tableData1.appendChild(numberNode);
                        tableData2.appendChild(textnode1);
                        tableData3.appendChild(textnode3);
                        tableData4.appendChild(textnode2);
                        eachGame.appendChild(tableData1);
                        eachGame.appendChild(tableData2);
                        eachGame.appendChild(tableData3);
                        eachGame.appendChild(tableData4);
                        eachGame.appendChild(tableData5);
                        document.getElementById("gameList").appendChild(eachGame)
                    }
                })
                .catch(response => console.log(response));
        },
        joinAgame: function (id) {
                fetch("/api/game/" + id + "/players", {
                        credentials: 'include',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                    })
                    .then(response => {
                            response.json().then(function (response) {
                                    console.log(response);
                                    window.location.replace("/web/game.html?gp=" + response.gamePlayerID.id)
                            })


                    })

            .catch(function (e) {
                console.log(e)

            })
    }

},

signUp: function () {
    var message = ""
    var userName = document.getElementById("username").value

    var password = document.getElementById("password").value
    console.log(password);
    fetch("/api/players", {
            credentials: 'include',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'userName=' + userName + '&password=' + password,
        })
        .then(response => {
            if (response.status == 201) {
                location.reload();
            }
        })
        .catch(function (e) {
            console.log(e)
        })
},

});
start();

function start() {
    console.log(1);

    var fetchConfig =
        fetch("/api/leaderboard", {
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
    main.leaderboard = data.sort((a, b) => b.totalScore - a.totalScore);
    main.player = data
    main.hideGameTable()



}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

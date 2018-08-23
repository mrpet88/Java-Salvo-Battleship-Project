

var main = new Vue({
    el: '#VueMain',
    data: {
//        games: [],
//        listObjects:[],
        leaderboard: [],
        currentSort:'totalScore',
        currentSortDir:'asc'
    },
    methods: {
        logIn:function(){
            fetch("/api/login", {
                   credentials: 'include',
                   method: 'POST',
                   headers: {
                       'Accept': 'application/json',
                       'Content-Type': 'application/x-www-form-urlencoded'
                   },
                   body: 'userName=' + username + '&password=' + password,
               })
        }

    }
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
//    main.games = data;
//    main.makeObject()
    main.leaderboard = data.sort((a,b) => b.totalScore - a.totalScore);
//    main.logIn()
     
  
    


}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

var main = new Vue({
    el: '#VueMain',
    data: {
        game_view: [],
        listObjects:[],
        rowNumbers:[1,2,3,4,5,6,7,8,9,10],
        colLetters:["A","B","C","D","E","F","G","H","I","J",]
    },
    methods: {
        makeObject: function () {
            var listObject = [];
            for (var i = 0; i < this.game_view.ships["0"].location.length; i++) {
                console.log(this.game_view.ships["0"].location[i])
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
        fetch("/api/game_view/1", {
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
       getHeadersHtml();
    renderHeaders();
}

function onConversionToJsonFailed() {
    console.log("Not a json mate!");
}

//function getHeadersHtml(data) {
//  return "<tr><th></th>" + data.destination_addresses.map(function(dest) {
//    return "<th>" + dest + "</th>";
//  }).join("") + "</tr>";
//}
//.ships["0"].location

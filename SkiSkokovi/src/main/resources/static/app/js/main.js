var wafepaApp = angular.module("wafepaApp",["ngRoute"]);

wafepaApp.controller("TakmicariCtrl", function($scope, $http, $location){
	
	var url = "/api/takmicari";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	$scope.search = {};
	$scope.search.ime = "";
	$scope.search.drzava = "";
	$scope.search.godisteOd = "";
	$scope.search.godisteDo = "";
	
	$scope.takmicari = [];

		var getTakmicari = function(){
		
		var config = {params: {}};
		if($scope.search.ime != ""){
			config.params.ime = $scope.search.ime;
		}
		if($scope.search.drzava != ""){
			config.params.drzava = $scope.search.drzava;
		}
		if($scope.search.godisteOd != ""){
			config.params.godisteOd = $scope.search.godisteOd;
		}
		if($scope.search.godisteDo != ""){
			config.params.godisteDo = $scope.search.godisteDo;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.takmicari = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch takmicari");
			}
		);
	}
	
	getTakmicari();
	

	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getTakmicari();
	}


	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getTakmicari();
	}
	

	$scope.goToEdit = function(id){
		$location.path("/takmicari/edit/" + id);
	}

	$scope.goToSkokAdd = function(id, skakaonicaId){
		console.log();
		$location.path("/skokovi/add/" + id + "/" + skakaonicaId);
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getTakmicari();
			},
			function error(){
				alert("Couldn't delete the takmicar.");
			}
		);
	}

	
});

wafepaApp.controller("AddTakmicarCtrl", function($scope, $http, $location){
	var url = "/api/takmicari";
	var urlSkakaonice = "/api/skakaonice";

	$scope.takmicar = {};
	$scope.skakaonice = [];

	var getSkakaonice = function(){
		var promise = $http.get(urlSkakaonice);
		promise.then(
			function success(res){
				$scope.skakaonice = res.data;
			},
			function error(){
				alert("Nisu dobavljene skakaonice.");
			}
		);
	}

	getSkakaonice();

	$scope.doAdd = function(){
		console.log($scope.takmicar);
		$http.post(url, $scope.takmicar).then(
			
			function success(){
				$location.path("/takmicari");
	
			},
			function error(){
				alert("Couldn't save the takmicar");
			}
		);
	}
});

wafepaApp.controller("EditTakmicarCtrl", function($scope, $http, $routeParams, $location){
	
	var url = "/api/takmicari/" + $routeParams.id;
	
	$scope.takmicar = {};
	$scope.takmicar.ime = "";
	$scope.takmicar.drzava = "";
	$scope.takmicar.visina = "";
	$scope.takmicar.godinaRodjenja = "";
	$scope.takmicar.email = "";


	var getTakmicar = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(res){
				$scope.takmicar = res.data;
			},
			function neuspeh(odg){
				alert("Couldn't fetch the takmicar.");
			}
		);
	}
	
	getTakmicar();

	$scope.doEdit = function(){
		var promise = $http.put(url, $scope.takmicar);
		console.log($scope.takmicar);
		promise.then(
			function success(){
				$location.path("/takmicari");
			},
			function error(){
				alert("Couldn't save the takmicar");
			}
		);
	}
	
});

wafepaApp.controller("AddSkokCtrl", function($scope, $http, $location, $routeParams){
	var url = "/api/skokovi";

	$scope.skok = {};
	$scope.skok.id = $routeParams.id;
	$scope.skok.skakaonicaId = $routeParams.skakaonicaId;
	$scope.skok.ocena1 = "";
	$scope.skok.ocena2 = "";
	$scope.skok.ocena3 = "";
	$scope.skok.ocena4 = "";
	$scope.skok.ocena5 = "";

	$scope.doAdd = function(){
		console.log($scope.ocene);
		var promise = $http.post(url, $scope.skok);
		promise.then(
			function success(){
				$location.path("/takmicari");
			},
			function error(){
				alert("Couldn't save the skok");
			}
		);
	}
});


wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/takmicari.html'
		})
		.when('/takmicari', {
			templateUrl : '/app/html/takmicari.html'
		})
		.when('/takmicari/add', {
			templateUrl : '/app/html/add-takmicar.html'
		})
		.when('/takmicari/edit/:id', {
			templateUrl : '/app/html/edit-takmicar.html'
		})
		.when('/skokovi/add/:id/:skakaonicaId', {
			templateUrl : '/app/html/add-skok.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);


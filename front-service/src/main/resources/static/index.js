(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl:'home/home.html',
                controller: 'homeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart',{
                templateUrl:'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.springMarketUser){
            try{
                let jwt = $localStorage.springMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt (new Date().getTime()/1000);
                if (currentTime >payload.exp){
                    console.log("Token is expired!!!");
                    delete  $localStorage.springMarketUser;
                    $http.defaults.headers.common.Authorization='';
                }
            } catch (e){
            }
            if ($localStorage.springMarketUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springMarketUser.token;
            }

            if (!$localStorage.springMarketGuestCartId) {
                $http.get('http://localhost:5555/cart/api/v1/cart/generate_id')
                    .then(function (response) {
                        $localStorage.springMarketGuestCartId = response.data.value;
                    });
            }
        }
    }
})();


angular.module('market').controller('indexController',function ($rootScope, $scope, $http, $location, $localStorage){
    const contextPath = 'http://localhost:5555/auth/'

    //Authorization
    $scope.tryToAuth = function () {
        $http.post(contextPath+'auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback (response){
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $scope.clearUser = function (){
        delete $localStorage.springMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function (){
        if($localStorage.springMarketUser){
            return true;
        } else {
            return false;
        }
    };

    $scope.checkAuth = function (){
        $http.get(contextPath+'auth_check').then(function (response) {
            alert(response.data.value);
        });
    };
});
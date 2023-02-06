angular.module('market').controller('cartController',function ($scope,$http,$location,$localStorage){
    const contextPath = 'http://localhost:5555/carts/'
    const coreContextPath = 'http://localhost:5555/core/'

    // корзинка

    $scope.loadCart = function () {
        $http.get(contextPath+'api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }
    $scope.addToCart = function (productId) {
        $http.get(contextPath+'api/v1/cart/add/'+productId).then(function (response) {
            $scope.loadCart();
        });
    }
    $scope.removeFromCart = function (productId) {
        $http.get(contextPath+'api/v1/cart/remove/'+productId).then(function (response) {
            $scope.loadCart();
        });
    }
    $scope.clearCart = function () {
        $http.get(contextPath+'api/v1/cart/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    // Заказы

    $scope.createOrder = function () {
        $http.post(coreContextPath+'api/v1/orders').then(function (response) {
            alert ("Order is created");
            $scope.loadCart();
        });
    }

    $scope.loadCart();
});
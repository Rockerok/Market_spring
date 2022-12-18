angular.module('my_market_front',[]).controller('appController',function ($scope,$http){
   $scope.loadProducts = function () {
       $http.get('http://localhost:8189/market_spring/api/v1/products').then(function (response) {
           // console.log(response.data);
           // let products = response.data();
           $scope.productsList = response.data;
       });
   }

   $scope.showProductInfo = function (productId) {
       $http.get('http://localhost:8189/market_spring/api/v1/products/'+productId).then(function (response) {
           alert(response.data.title);
       });
   }

    $scope.deleteProductById = function (productId) {
        $http.delete('http://localhost:8189/market_spring/api/v1/products/'+productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/market_spring/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function () {
        $http.put('http://localhost:8189/market_spring/api/v1/cart/add/'+productId).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.deleteFromCartById = function () {
        $http.delete('http://localhost:8189/market_spring/api/v1/cart/delete/'+productId).then(function (response) {
            $scope.cart = response.data;
        });
    }

   $scope.loadProducts();
});
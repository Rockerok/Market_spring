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
        $http.delete('http://localhost:8189/market_spring/api/v1/products/delete/'+productId).then(function () {
            $scope.loadProducts();
        });
    }

    // корзинка

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/market_spring/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }
    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/market_spring/api/v1/cart/add/'+productId).then(function (response) {
            $scope.loadCart();
        });
    }
    $scope.deleteFromCartById = function (productId) {
        $http.delete('http://localhost:8189/market_spring/api/v1/cart/delete/'+productId).then(function () {
            $scope.loadCart();
        });
    }
    $scope.deleteCart = function () {
       // if(confirm("Вы точно хотите удалить текущую корзину?")) {
       //     $http.delete('http://http://localhost:8189/market_spring/api/v1/cart/new').then(function () {
       //     });
       // }
        $http.delete('http://localhost:8189/market_spring/api/v1/cart/new').then(function () {
            $scope.loadCart();
        });
    }

    $scope.loadCart();
    $scope.loadProducts();
});
angular.module('market').controller('storeController',function ($scope,$http,$location,$localStorage){
    const contextPath = 'http://localhost:5555/core/'
    const cartContextPath = 'http://localhost:5555/cart/'
    // products

   $scope.loadProducts = function () {
       $http.get(contextPath+'api/v1/products')
           .then(function (response) {
                $scope.productsList = response.data;
       });
   }

   $scope.showProductInfo = function (productId) {
       $http.get(contextPath+'api/v1/products/'+productId)
           .then(function (response) {
                alert(response.data.title);
       });
   }

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath+ 'api/v1/products/delete/'+productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    // корзинка

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath+'api/v1/cart/'+ $localStorage.springMarketGuestCartId +'/add/'+productId)
            .then(function (response) {
              // $scope.loadCart();
        });
    }
    $scope.loadProducts();
});
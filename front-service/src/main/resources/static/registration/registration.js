angular.module('market').controller('registrationController', function ($scope, $http,$local, $localStorage) {
    const contextPath = 'http://localhost:5555/auth/'

    $scope.functionRegistration = function () {
        $http.post(contextPath+"registration", $scope.reguser).then(function (response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.springMarketUser = {username: $scope.reguser.username, token: response.data.token};
                $localStorage.reguser = null;
                $local.path("/");
            }
        });
    };

});
'use strict';

(() => {
  angular
    .module('flights.user')
    .service('userService', UserService);

  UserService.$inject = ['$http', '$log', 'accessService'];

  function UserService($http, $log, accessService) {
    $log.debug('userService-init')

    this.getUser = function(username) {
      return $http
        .get('./api/users/' + username)
        .then(response => response.data)
    };

  }
})();

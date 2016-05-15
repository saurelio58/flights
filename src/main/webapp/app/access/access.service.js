'use strict';

(() => {
  angular
    .module('flights.access')
    .service('accessService', AccessService);

  AccessService.$inject = ['bcrypt', '$http', '$log', '$state'];

  function AccessService(bcrypt, $http, $log, $state) {
    $log.debug('AccessService-init')

    this.currentUser

    this.register = (user) => {
      $log.debug('AccessService.register-init')
      let salt = bcrypt.genSaltSync(4);
      let hash = bcrypt.hashSync(user.password, salt);
      user.password = hash;

      return $http
        .post('./api/users', user)
        .then(response => response.data)
        .then(user => {
          if (user.username == null) {
            //  user already exists
            return null;
          } else {
            this.currentUser = user
            $state.go('selectTrip')
            $log.debug('AccessService.register-$state.go failed-we should not get here');
          }
        });
    };

    this.login = (credentials) => {
      $log.debug('AccessService.login-init')
      return $http
        .get('./api/users/' + credentials.username) // returns response
        .then(response => response.data) // t response, r user
        .then(user => {
          if (user.username == null) {
            // user not found
            $log.debug('AccessService.login-user not found')
            return null;
          } else {
            if (bcrypt.compareSync(credentials.password, user.password)) {
              $log.debug('AccessService.login-user authenticated successfully');
              this.currentUser = user;
              delete this.currentUser.password;
              credentials = undefined;
              $state.go('selectTrip');
              $log.debug('AccessService.login-$state.go failed-we should not get here');
            } else {
              $log.debug('AccessService.login-invalid username or password');
              this.currentuser = undefined;
            }
          }
        })
        .catch(error => $log.debug(JSON.stringify(error)));
    }

    this.logout = () => {
      this.currentUser = undefined;
      $state.go('login')
    }

    this.isLoggedOut = () => this.currentUser == undefined;

    this.isLoggedIn = () => this.currentUser !== undefined;


  }

})();

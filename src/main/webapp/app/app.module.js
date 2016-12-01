'use strict';

(() => {
  angular
    .module('flights', [
      'ngMaterial',
      'ngMessages',
      'ui.router',
      'dtrw.bcrypt',
      'flights.user',
      'flights.flight',
      'flights.access'
    ])

})()

'use strict';

(() => {
  angular
    .module('fastbook.user')
    .service('userService', UserService);

  UserService.$inject = ['$http', '$log', 'accessService'];

  function UserService($http, $log, accessService) {

    this.listOfUsers;
    this.profileUser;
    this.post;

    this.setProfileUser = (user) => {
      $log.debug(user);
      this.profileUser = user;
      $log.debug('Profile user: ' + this.profileUser)
      $log.debug('Last Name: ' + this.profileUser.lastName)
    };

    this.getAllUsers = function() {
      return $http
        .get('./api/users/')
        .then(response => response.data);
    };

    this.getUsersByName = function(name) {
      return $http
        .get('./api/users/find/' + name)
        .then(response =>{
          $log.debug(response);
          return response.data;
        });
      };

    this.getUserById = function(id) {
      return $http
        .get('./api/users/' + id)
        .then(response => response.data)
    };

    this.getUserFriends = function(id) {
          return $http
            .get('./api/users/' + id + '/friends')
            .then(response => response.data)
        };

    this.getUserGroups = function(id) {
          return $http
            .get('./api/users/groups/' + id)
            .then(response => response.data)
    };

    this.getUsersPosts = (userId) =>{
      return $http
        .post('./api/posts/user/getPosts/' + userId, accessService.currentUser)
        .then(response =>{
          $log.debug(response.data);
          return response.data;
        });
      }

    this.postToUserTimeline = (userId, post) =>{
      return $http
        .post('./api/posts/user/' + userId, post)
        .then(response => response.data)
    };

    this.likeUserPost = () => {
      
    }
  };
})();

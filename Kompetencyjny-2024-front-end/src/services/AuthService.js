import http from "../http-common";

class authService {
  registerUser(data) {
    return http.post(`/auth/register`, data);
  }
  loginUser(data) {
    return http.post(`/auth/authenticate`, data);
  }
  getUserByToken() {
    return http.get(`/auth/user-token`, {
      headers:{
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
    });
  }

  logoutUser() {
    return http.get(`/auth/logout`, {
        headers:{
            Authorization: 'Bearer ' + localStorage.getItem('token')
          }
      });
  }
}

export default new authService();

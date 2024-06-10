import http from "../http-common";
function getAuthHeaders() {
    const token = localStorage.getItem('token');
    if (token) {
      return {
        Authorization: 'Bearer ' + token
      };
    }
    return {};
  }
class predictionService {
    
    smallModelPredict(data) {
        return http.post(`/model/small/prediction`, data, {
          headers: getAuthHeaders()
        });
    }
    mediumModelPredict(data) {
        return http.post(`/model/medium/prediction`, data, {
          headers: getAuthHeaders()
        });
    }
    bigModelPredict(data) {
        return http.post(`/model/big/prediction`, data, {
          headers: getAuthHeaders()
        });
    }
}

export default new predictionService();

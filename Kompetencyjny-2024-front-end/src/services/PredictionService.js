import http from "../http-common";

class predictionService {
    smallModelPredict(data) {
        return http.post(`/model/small/prediction`, data, {
          headers:{
              Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        });
    }
    mediumModelPredict(data) {
        return http.post(`/model/medium/prediction`, data, {
          headers:{
              Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        });
    }
    bigModelPredict(data) {
        return http.post(`/model/big/prediction`, data, {
          headers:{
              Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        });
    }
}

export default new predictionService();

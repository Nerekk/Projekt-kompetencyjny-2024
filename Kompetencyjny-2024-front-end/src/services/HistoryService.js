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
class historyService {
    
    fetchStatistics() {
        return http.get(`/statistics/most-used`);
    }
    fetchPaginatedHistory(page) {
        return http.get(`/history?page=${page}`, {
            headers: getAuthHeaders()
        });
    }
}

export default new historyService();

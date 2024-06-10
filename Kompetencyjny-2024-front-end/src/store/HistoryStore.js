import { defineStore } from 'pinia';
import historyService from '@/services/HistoryService';

export const useHistoryStore = defineStore('historyStore', {
    state: () => ({
        mostUsedParams: null,
        paginatedHistory: [],
    totalPages: 0,
    currentPage: 0,
    }),
    actions: {
       async fetchStatistics() {
            if(!this.mostUsedParams){
                const response = await historyService.fetchStatistics();
                this.mostUsedParams = response.data
            }
        },
        async fetchPaginatedHistory(page) {
            if (this.paginatedHistory[page]) {
              return;
            }
      
            try {
              const response = await historyService.fetchPaginatedHistory(page);
              const data = response.data;
              
              this.paginatedHistory[page] = data.content;
              
              this.totalPages = data.totalPages;
              this.currentPage = page;
            } catch (error) {
              console.error('Failed to fetch paginated history:', error);
            }
          },
    },
});

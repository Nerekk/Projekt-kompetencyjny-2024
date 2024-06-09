import { defineStore } from 'pinia';
import AuthService from '@/services/AuthService';

export const useAuthStore = defineStore('authStore', {
    state: () => ({
        user: null,
    }),
    actions: {
        setUser(user) {
            this.user = user;
        },
        async registerUser(userFormatted) {
            const response = await AuthService.registerUser(userFormatted);
            localStorage.setItem('token', response.data.access_token);
            this.setUser(response.data.user);
            await this.getUserByToken();
        },
        async getUserByToken() {
            const response = await AuthService.getUserByToken();
            this.setUser(response.data);
        },
        async loginUser(userFormatted) {
            const response = await AuthService.loginUser(userFormatted);
            localStorage.setItem('token', response.data.access_token);
            this.setUser(response.data.user);
            await this.getUserByToken();
        },
        async logoutUser() {
            await AuthService.logoutUser();
            localStorage.removeItem('token');
            this.setUser(null);
        },
    },
});

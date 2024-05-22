import { createStore } from "vuex";
import AuthService from '@/services/AuthService';
const store = createStore({
    state: {
        user: null,
    },
    mutations: {
        setUser(state, user) {
            state.user = user
        },
    },
    actions: {
        //Auth
        async registerUser({ commit }, userFormatted) {
            try {
                const response = await AuthService.registerUser(userFormatted);
                localStorage.setItem('token', response.data.access_token);
                await commit('setUser', response.data.user);
                await store.dispatch('getUserByToken');
            } catch (e) {
                console.error('Registration failed:', e.response);
            }
        },
        async getUserByToken({ commit }) {
            try {
                const response = await AuthService.getUserByToken();
                commit('setUser', response.data);
            } catch (e) {
                console.log("User not logged in")
            }
        },
        async loginUser({ commit }, userFormatted) {
            try {
                const response = await AuthService.loginUser(userFormatted);
                localStorage.setItem('token', response.data.access_token);
                await commit('setUser', response.data.user);
                await store.dispatch('getUserByToken');
            } catch (e) {
                console.error('Login failed:', e.response);
            }
        },
        async logoutUser({ commit }) {
            try {
                await AuthService.logoutUser();
                localStorage.removeItem('token');
                commit('setUser', null);
            } catch (e) {
                console.error('Logout failed:', e.response);
            }
        },
    },
})

export default store
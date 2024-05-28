import { createStore } from "vuex";
import AuthService from '@/services/AuthService';
const store = createStore({
    state: {
        user: null,
        overlay: false,
    },
    mutations: {
        setUser(state, user) {
            state.user = user
        },
        setShowOverlay(state) {
            state.overlay = true
        },
    },
    actions: {
        showOverlay({ commit }) {
            commit('setShowOverlay')
        },
        //Auth
        async registerUser({ commit }, userFormatted) {
            const response = await AuthService.registerUser(userFormatted);
            localStorage.setItem('token', response.data.access_token);
            await commit('setUser', response.data.user);
            await store.dispatch('getUserByToken');
        },
        async getUserByToken({ commit }) {
            const response = await AuthService.getUserByToken();
            commit('setUser', response.data);
        },
        async loginUser({ commit }, userFormatted) {
            const response = await AuthService.loginUser(userFormatted);
            localStorage.setItem('token', response.data.access_token);
            await commit('setUser', response.data.user);
            await store.dispatch('getUserByToken');
        },
        async logoutUser({ commit }) {
            await AuthService.logoutUser();
            localStorage.removeItem('token');
            commit('setUser', null);
        },
    },
})

export default store
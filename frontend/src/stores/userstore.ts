import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  state: () => ({
    id: null
  }),

  getters: {
    getId(state) {
      return state.id
    }
  },

  actions: {
    setId(id: string) {
      this.id = id
    }
  }
});

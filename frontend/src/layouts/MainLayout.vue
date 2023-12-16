<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title>
          <q-avatar
            rounded
            color="primary"
            text-color="white"
            icon="webhook"
            size="4.4em"
          />
          গরুর মাংসের অর্ডার ম্যানেজমেন্ট
        </q-toolbar-title>

        <div>
          <strong
            >“স্বপ্ন সেটা নয় যেটা মানুষ, ঘুমিয়ে ঘুমিয়ে দেখে; স্বপ্ন সেটাই যেটা
            পূরনের প্রত্যাশা, মানুষকে ঘুমাতে দেয় না।” – এ.পি.জে.আব্দুল
            কালাম</strong
          >
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-img
        class="absolute-top"
        src="https://cdn.quasar.dev/img/material.png"
        style="height: 150px"
      >
        <div class="absolute-bottom bg-transparent">
          <q-avatar size="56px" class="q-mb-sm">
            <img src="https://cdn.quasar.dev/img/boy-avatar.png" />
          </q-avatar>
          <div class="text-weight-bold">{{ userId.name }}</div>
          <div>{{ userId.email }}</div>
        </div>
      </q-img>
      <q-scroll-area
        style="
          height: calc(100% - 150px);
          margin-top: 150px;
          border-right: 1px solid #ddd;
        "
      >
        <q-list>
          <q-item-label header> প্রয়োজনীয় লিংক </q-item-label>

          <EssentialLink
            v-for="link in essentialLinks"
            :key="link.title"
            v-bind="link"
          />
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-chip square icon="copyright">cloudcentrik systems 2023</q-chip>
          <q-chip
            outline
            square
            color="white"
            text-color="white"
            icon="contact_phone"
            >ডেভেলপার: মোঃ ইসমাইল ফকির, যোগাযোগ করুন: ০৭০৪৩৫৮৮৪২</q-chip
          >
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from 'vue';
import { useUserStore } from 'stores/userstore';
import { storeToRefs } from 'pinia';
import EssentialLink from 'components/EssentialLink.vue';

const linksList = [
  {
    title: 'মাংসের অর্ডার',
    caption: 'গরুর মাংসের অর্ডার',
    icon: 'post_add',
    link: '/beeforder',
  },
  {
    title: 'নোট',
    caption: 'করণীয় নোট',
    icon: 'ballot',
    link: '/todos',
  },
  /* {
    title: 'হোম পেজ',
    caption: 'হোম পেজ',
    icon: 'home',
    link: '/home',
  }, */
  {
    title: 'পোস্ট',
    caption: 'ব্যবহারকারীর পোস্ট',
    icon: 'event_note',
    link: '/posts',
  },
  {
    title: 'প্রোফাইল',
    caption: 'ব্যবহারকারীর প্রোফাইল',
    icon: 'person',
    link: '/profile',
  },
  {
    title: 'সাহায্য',
    caption: 'সাহায্য',
    icon: 'help',
    link: '/help',
  },
  {
    title: 'লগআউট',
    caption: 'লগআউট',
    icon: 'logout',
    link: '/auth/login',
  },
];

export default defineComponent({
  name: 'MainLayout',

  components: {
    EssentialLink,
  },

  setup() {
    const store = useUserStore();
    const userId = computed(() => store.id);

    const leftDrawerOpen = ref(false);

    return {
      userId,
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
});
</script>

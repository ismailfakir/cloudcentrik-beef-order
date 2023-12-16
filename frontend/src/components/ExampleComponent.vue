<template>
  <q-card
    class="my-card text-white"
    style="background: radial-gradient(circle, #35a2ff 0%, #014a88 100%)"
  >
    <q-card-section>
      <div class="text-h6">
        <p>{{ title }}</p>
      </div>
      <div class="text-subtitle2">Todos</div>
    </q-card-section>

    <q-card-section>
      <q-table
        title="Todos"
        :rows="todos"
        :columns="columns"
        row-key="name"
        @click="increment"
        selection="single"
        v-model:selected="selected"
      />
    </q-card-section>

    <q-separator dark />

    <q-card-section>
      <div class="text-h7">
        <p>Count: {{ todoCount }} / {{ meta.totalCount }}</p>
      </div>
      <div class="text-h7">
        <p>Active: {{ active ? 'yes' : 'no' }}</p>
      </div>
      <div class="text-h7">
        <p>Clicks on todos: {{ clickCount }}</p>
      </div>
    </q-card-section>
  </q-card>
</template>

<script lang="ts">
import { defineComponent, PropType, computed, ref, toRef, Ref } from 'vue';
import { Todo, Meta } from './models';

function useClickCount() {
  const clickCount = ref(0);
  function increment() {
    clickCount.value += 1;
    return clickCount.value;
  }

  return { clickCount, increment };
}

function useDisplayTodo(todos: Ref<Todo[]>) {
  const todoCount = computed(() => todos.value.length);
  return { todoCount };
}

export default defineComponent({
  name: 'ExampleComponent',
  columns: [{ name: 'id' }, { name: 'content' }],

  props: {
    title: {
      type: String,
      required: true,
    },
    todos: {
      type: Array as PropType<Todo[]>,
      default: () => [],
    },
    meta: {
      type: Object as PropType<Meta>,
      required: true,
    },
    active: {
      type: Boolean,
    },
  },
  setup(props) {
    return {
      ...useClickCount(),
      ...useDisplayTodo(toRef(props, 'todos')),
    };
  },
});
</script>

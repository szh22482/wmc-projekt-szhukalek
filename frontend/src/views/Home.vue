<template>
  <v-card
    flat
    title="Users"
  >
    <template v-slot:text>
      <v-text-field
        v-model="search"
        label="Search"
        prepend-inner-icon="mdi-magnify"
        single-line
        variant="outlined"
        hide-details
      ></v-text-field>
    </template>

    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
          size="small"
          class="me-2"
          @click="editItem(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          size="small"
          @click="deleteItem(item)"
        >
          mdi-delete
        </v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn
          color="primary"
          @click="initialize"
        >
          Reset
        </v-btn>
      </template>
    </v-data-table>
  </v-card>
</template>

<script setup>
  import axios from "axios";
</script>

<script>
  import axios from "axios";

  export default {
    data() {
      return {
        search: '',
        headers: [
          {title: "ID", key: "id"},
          {title: "Firstname", key: "firstname"},
          {title: "Lastname", key: "lastname"},
          {title: "Created", key: "created"},
          {title: "Roles", key: "roles"},
          {title: "Actions", key: "actions"},
        ],
        users: []
      }
    },
    async mounted() {
      try {
        const response = await axios.get("/users/all");
        if(response != null) {
          this.users =  response.data;
          console.log(this.users, response)
        } else {
          alert("No users found");
        }
      } catch (e) {
        alert(e);
      }
    }
  }

</script>

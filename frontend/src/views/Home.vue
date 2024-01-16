<template>
  <v-container class="main-con">
    <v-data-table :items="users">
      <template v-slot:headers="props">
        <thead>
        <tr>
          <th v-for="header in props.headers" :key="header.text">
            {{ header.text }}
          </th>
        </tr>
        </thead>
      </template>
      <template v-slot:items="props">
        <tbody>
        <tr v-for="item in props.items" :key="item.id">
          <td>{{ item.vorname }}</td>
          <td>{{ item.nachname }}</td>
          <td>{{ item.email }}</td>
          <!-- Füge weitere Spalten hier hinzu -->
          <td>{{ item.roles.join(', ') }}</td> <!-- Beispiel für Rollen -->
          <td>
            <v-btn @click="handleButtonClick(item)">Button</v-btn>
          </td>
        </tr>
        </tbody>
      </template>
    </v-data-table>
  </v-container>
</template>

<script setup>
  import axios from "axios";
</script>

<script>
  import axios from "axios";

  export default {
    data() {
      return {
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

<template>
    <div id = "index-container" class = "index-container">
        <header class = "index-header">
            <nav class = "index-top-nav">
                <i @click = "handleClickAuth('login')">登录</i>
                <i @click = "handleClickAuth('register')">注册</i>
            </nav>
            <section class = "index-top-banner"></section>
        </header>
        <main>
            <section class = "main-section main-section-1">
                <div class = "search-container">
                    <div>
                        <el-input
                            type = "text"
                            v-model = "search"
                            class = "search-input"
                            prefix-icon = ""
                            minlength = "1"
                            maxlength = "32"
                            placeholder = "搜索"
                            autofocus = "true"
                            tabindex = "1"
                            @keydown = "handleSearchInput"
                            @keydown.enter = "handleSearchButton"
                            />
                        <span class = "search-button" @click="handleSearchButton"></span>
                    </div>
                </div>
                <div class = "search-dropdown">
                    <ul>
                    </ul>
                </div>
            </section>
            <section lass = "main-section main-section-2"></section>
            <section lass = "main-section main-section-3"></section>
            <section lass = "main-section main-section-4"></section>
            <section lass = "main-section main-section-5"></section>
            <section lass = "main-section main-section-6"></section>
        </main>
        <footer></footer>
    </div>
</template>

<script>

import Vue from 'vue';
import { mapState } from 'vuex';
import { Input } from 'element-ui';

Vue.component(Input.name, Input);

export default {
    data:() => {
        return {
            search: '1',
        };
    },
    methods: {
        handleClickAuth(type) {

            if (this.auth.uid) {
                return;
            }

            return this.$router.push({
                name: 'auth',
                query: {
                    type,
                    target: encodeURIComponent(location.href)
                }
            });
        },
        handleSearchInput(e) {
            console.log(this.search);
            const { search } = this.data;
        },
        handleSearchButton(e) {
            console.log(this.search);
            const { search } = this.data;
        },
    },
    computed: {
        ... mapState(['auth']),
    },
};

</script>

<style>

    .index-container {
        padding: 0.1rem;
    }

    .index-header {
        display: flex;
        font-size: 0.3rem;
        padding-top: 0.3rem;
        flex-direction: column-reverse;
    }

/*    .index-header .index-top-banner {
        max-height: 1rem;
        min-height: 1rem;
    }*/

    .index-header .index-top-nav {
        padding: 0.09rem;
        text-align: right;
    }

    .index-header .index-top-nav i {
        font-style: normal;
        padding: 0 0.05rem;
        cursor: pointer;
    }

    .index-header .index-top-nav i:last-child {
        padding: 0 0.1rem 0 0;
    }

    .index-header .index-top-nav i:hover {
        color: green;
    }

    .main-section {
        margin: 0 0.2rem;
    }

    .main-section-1 {
        margin-top: 1.5rem;
    }

    .main-section-1 .search-container {
        margin: 0 auto;
        max-width: 7.5rem;
    }

    @media screen and (min-width: 750px) {
        .index-container .index-header {
            margin-top:;
        }
        .main-section.main-section-1 {
            margin-top: 0.2rem;
        }
    }
</style>

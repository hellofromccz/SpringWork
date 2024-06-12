import axios from 'axios';

const instance = axios.create({
  //baseURL: 'http://172.30.200.230:8080', // 这一行的baseURL要改成新获取的WSL IP地址 + 端口号
  baseURL: 'http://localhost:8084',
  withCredentials: false,
});

export default instance;

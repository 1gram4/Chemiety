import axios from 'axios'

// axios.interceptors.request.use(function (config) {
//   config.headers.common = {
//     'Content-Type': 'application/x-www-form-urlencoded'
//   }
//   return config
// }, function (error) {
//   return Promise.reject(error)
// })
axios.defaults.baseURL = 'http://chemiety-admin.kastner.cn'
// axios.defaults.withCredentials = false
axios.defaults.headers.common = {
  'Content-Type': 'application/x-www-form-urlencoded'
}

export default {
  getAllInfo: () => axios.get('/api/v1/infos'),
  getAllFiles: (fileType) => axios.get('/api/v1/admin/getAllFiles', {params: {fileType: fileType}}),
  getAllPost: () => axios.get('/api/v1/posts'),
  postComment: ({content, id, username}) => axios.post(`/api/v1/posts/${id}/comments?content=${content}&username=${username}`),
  postPost: ({title, content, username}) => axios.post(`/api/v1/posts?title=${title}&content=${content}&username=${username}`)
}

import Cookie from 'js-cookie'

/**
 * @description 存储 cookie 值
 * @param {String} name cookie name
 * @param {String} value cookie value
 * @param {Object} cookieSetting cookie config
 */
export const setCookie = (name = "default", value = '', cookieSetting = {}) => {
  let currentCookieSetting = {
    expires: 1
  }
  Object.assign(cookieSetting, cookieSetting)
  Cookie.set(`${name.toUpperCase()}`, value, currentCookieSetting)
}

/**
 * @description 获取 cookie 值
 * @param {String} name cookie name
 * @returns {*}
 */
export const getCookie = (name = 'default') => {
  return Cookie.get(`${name.toUpperCase()}`)
}

/**
 * @description 获取所有 cooke 值
 * @returns {*}
 */
export const getAllCookie = () => {
  return Cookie.get()
}

/**
 * @description 删除 cookie
 * @param {String} name
 * @returns {*|void}
 */
export const removeCookie = (name = 'default') => {
  return Cookie.remove(`${name.toUpperCase()}`)
}

export default {
  setCookie,
  getCookie,
  getAllCookie,
  removeCookie
}

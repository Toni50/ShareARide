import axios from '../custom-axios/axios'
import Cookie from "js-cookie";


const RepositoryService = {


    handleServerError: (error) => {
        console.log("handleServerError");
        console.log(error);

        if(error.response!=null){
            if(error.response.status === 403 ){
                Cookie.remove('jwt');
                window.location.href = "/signin";
            }else{
                console.log(error.response.data.message);
                alert("Problem message: "+error.response.data.message);
            }
        }else{
            alert("server is not responding");
        }
    },


    logIn: (user) => {
        return axios.post("/sharearide/rest/authentication/authenticate", user)
    },


    register: (user) => {
        return axios.post("/sharearide/rest/user/register", user)
    },

    getUser: (userId) => {
        return axios.get("/sharearide/rest/user/getUserById",
            {
                params: {id: userId},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },
    updateUser(user) {
        return axios.put(
            "/sharearide/rest/user/updateUser", user,
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },
    getAccountInfo: () => {
        return axios.get("/sharearide/rest/user/getAccountInfo",
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },

    fetchNotifications: (page=0,pageSize=5) => {
        return axios.get("/sharearide/rest/notification/fetchNotifications",
            {
                params: {page: page,pageSize:pageSize},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    acceptRideRequest: (requestId) => {
        return axios.put("/sharearide/rest/rideRequest/accept", {},
            {
                params: {requestId: requestId},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },
    rejectRideRequest: (requestId) => {
        return axios.put(
            "/sharearide/rest/rideRequest/reject", {},
            {
                params: {requestId: requestId},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    addRideRequest: (data) => {
        return axios.post("/sharearide/rest/rideRequest/addRideRequest", data,
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    findARide: (data) => {
        return axios.post("/sharearide/rest/ride/findaride", data,
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    offerRide: (data) => {
        return axios.post("/sharearide/rest/ride/addRide", data,
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    getHistoryRides: (page=0,pageSize=5) => {
        return axios.get("/sharearide/rest/rideRequest/getHistoryRides",
            {
                params: {page: page,pageSize:pageSize},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    getUpcomingRides: (page=0,pageSize=5) => {
        return axios.get("/sharearide/rest/rideRequest/getUpcomingRides",
            {
                params: {page: page,pageSize:pageSize},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    getRideRequests: (page=0,pageSize=5) => {
        return axios.get("/sharearide/rest/rideRequest/getRideRequests",
            {
                params: {page: page,pageSize:pageSize},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },


    deleteCar: (carId) => {
        return axios.delete("/sharearide/rest/car/deleteCar",
            {
                params: {id: carId},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },

    addCar: (car) => {
        return axios.post("/sharearide/rest/car/addCar", car,
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            })
    },

    addReview: (data) => {
        return axios.post("/sharearide/rest/review/addReview", data,
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            })
    },
    getAllReviewsForUser: (userId) => {
        return axios.get("/sharearide/rest/review/getAllReviewsForUser",
            {
                params: {userId: userId},
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            })
    },



    fetchCars: () => {
        return axios.get("/sharearide/rest/car",
            {
                headers: {
                    'Authorization': "Bearer " + Cookie.get("jwt")
                }
            }
        )
    },



};

export default RepositoryService;
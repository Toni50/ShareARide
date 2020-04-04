import React, {useState, useEffect} from 'react'
import 'date-fns';
import NotificationItem from "./notificationItem";
import RepositoryService from "../../custom-axios/repository";
import ReactPaginate from "react-paginate";
import RideItem from "../rides/rideItem";


const Notifications = (props) => {

    const [notifications, setNotifications] = React.useState([]);

    useEffect(() => {
        loadItems()
    }, []);


    function loadItems(page=0) {
        RepositoryService.fetchNotifications(page)
            .then((response) => {
                setNotifications(response.data)
            }).catch(RepositoryService.handleServerError);
    }

    const handlePageClick = (e) => {
        loadItems(e.selected)
    };

    const paginate = () => {
        if (props.totalPages !== 0) {
            return (
                <ReactPaginate previousLabel={"previous"}
                               nextLabel={"next"}
                               breakLabel={<span className="gap">...</span>}
                               breakClassName={"break-me"}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               pageClassName={"page-item"}
                               pageLinkClassName={"page-link"}
                               previousClassName={"page-item"}
                               nextClassName={"page-item"}
                               previousLinkClassName={"page-link"}
                               nextLinkClassName={"page-link"}
                               containerClassName={"pagination justify-content-center"}
                               activeClassName={"active"}
                               forcePage={notifications.page}
                               pageCount={notifications.totalPages}
                               onPageChange={handlePageClick}
                />
            )
        }
    }



    function showNotifications() {
        let data;

        if(notifications.content!=null){
            data = notifications.content.map((item,index) => {
                return (
                    <NotificationItem
                        key={index}
                        data={item}
                    />
                );
            });
        }

        return (
            <div>
                {data}
                {paginate()}
            </div>
        )

    }


    return (
        <div>
            <h1>Notifications</h1>

            {showNotifications()}

        </div>
    );


};


export default Notifications;
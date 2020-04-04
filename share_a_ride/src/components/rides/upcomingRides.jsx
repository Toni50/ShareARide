import React, {useEffect, useState} from "react";
import RepositoryService from "../../custom-axios/repository";
import ReactPaginate from "react-paginate";
import RideItem from "./rideItem";


const UpcomingRides = (props) => {

    const [upcoming, setUpcoming] = useState([]);


    useEffect(() => {
        loadItems()
    }, []);


    function loadItems(page=0) {
        RepositoryService.getUpcomingRides(page,5)
            .then((response) => {
                console.log("get Upcoming");
                console.log(response);
                setUpcoming(response.data)
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
                               forcePage={upcoming.page}
                               pageCount={upcoming.totalPages}
                               onPageChange={handlePageClick}
                />
            )
        }
    }


    function showUpcoming() {
        let data;
        if(upcoming.content!=null){
            data = upcoming.content.map((item) => {
                return (
                    <RideItem key={item.requestId} data={item}/>
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
            {showUpcoming()}
        </div>
    );
};
export default UpcomingRides;
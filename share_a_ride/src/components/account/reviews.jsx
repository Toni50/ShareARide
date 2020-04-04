import React, {useState, useEffect} from 'react'
import 'date-fns';
import RepositoryService from "../../custom-axios/repository";
import ReviewItem from "./reviewItem";

const Reviews = (props) => {

    const [reviews, setReviews] = React.useState([]);

    useEffect(() => {
        RepositoryService.getAllReviewsForUser(props.userId)
            .then((response) => {
                console.log(response);
                if (response.status === 200) {
                    setReviews(response.data)
                }
            }).catch(RepositoryService.handleServerError);
    }, []);

    function showReviews() {
        return (
            reviews.map((item)=>{
                return (
                    <ReviewItem
                        key={item.reviewId}
                        data={item}
                    />
                )
            })
        )
    }

    return (
        <div>
            {showReviews()}
        </div>
    );

};

export default Reviews;
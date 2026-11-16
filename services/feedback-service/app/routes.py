from fastapi import APIRouter
from typing import List
from .models import Feedback, FeedbackResponse

router = APIRouter()

# Temporary in-memory database for testing
fake_db: List[dict] = []

@router.post("/", response_model=FeedbackResponse)
def submit_feedback(feedback: Feedback):
    new_id = len(fake_db) + 1
    new_feedback = feedback.model_dump()
    new_feedback["id"] = new_id
    fake_db.append(new_feedback)
    return new_feedback

@router.get("/", response_model=List[FeedbackResponse])
def get_all_feedback():
    return fake_db

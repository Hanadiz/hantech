from pydantic import BaseModel

class Feedback(BaseModel):
    customer_name: str
    service_used: str
    rating: int
    comments: str = ""

class FeedbackResponse(Feedback):
    id: int

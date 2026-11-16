from fastapi import FastAPI
from .routes import router

app = FastAPI(
    title="Han-Tech Feedback Service",
    description="Microservice for collecting customer feedback",
    version="1.0.0"
)

app.include_router(router, prefix="/feedback", tags=["Feedback"])

@app.get("/")
def health_check():
    return {"status": "healthy", "service": "feedback-service"}

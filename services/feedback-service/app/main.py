
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from .routes import router

app = FastAPI(
    title="Han-Tech Ltd ",
    description="Backend for community IT services and feedback",
    version="1.0.0"
)

# Allow your web frontend to talk to this backend
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"], # In production, you can change this to your GitHub Pages URL
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(router, prefix="/feedback", tags=["Feedback"])

@app.get("/")
def health_check():
    return {"status": "healthy", "company": "Han-Tech Ltd"}

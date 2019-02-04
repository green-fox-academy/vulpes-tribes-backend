ALTER TABLE resources
ADD COLUMN generated_amount BIGINT(10) AFTER updated_at;
package movie.finalproj.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	
	@Column(name = "name")
	private String reviewerName;
	private String reviewText;
	private String stars;
	
	@CreationTimestamp
	@Column(name = "created_timestamp")
	private LocalDateTime createdTimeStamp;
		
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private Movie movie;
}  // end of Reviews class

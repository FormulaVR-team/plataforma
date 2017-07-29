var img2DObj = function() {

	function dataURLToBlob (dataURL) {
	    var BASE64_MARKER = ';base64,';
	    if (dataURL.indexOf(BASE64_MARKER) == -1) {
	        var parts = dataURL.split(',');
	        var contentType = parts[0].split(':')[1];
	        var raw = parts[1];

	        return new Blob([raw], {type: contentType});
	    }

	    var parts = dataURL.split(BASE64_MARKER);
	    var contentType = parts[0].split(':')[1];
	    var raw = window.atob(parts[1]);
	    var rawLength = raw.length;

	    var uInt8Array = new Uint8Array(rawLength);

	    for (var i = 0; i < rawLength; ++i) {
	        uInt8Array[i] = raw.charCodeAt(i);
	    }

	    return new Blob([uInt8Array], {type: contentType});
	}

	return{
		  una_cosa: function () {
			alert("Hola");
		  }
		, base64_resize: function (base64,max_size_pixels) {
			var image = new Image();
			image.onload = function (imageEvent) {
			    // Resize the image
			    var canvas = document.createElement('canvas'),
			        max_size = max_size_pixels, //544,// TODO : pull max size from a site config
			        width = image.width,
			        height = image.height;
			    if (width > height) {
			        if (width > max_size) {
						height *= max_size / width;
						width = max_size;
			        }
			    } else {
			        if (height > max_size) {
						width *= max_size / height;
						height = max_size;
			        }
			    }
			    canvas.width = width;
			    canvas.height = height;
			    canvas.getContext('2d').drawImage(image, 0, 0, width, height);
			    dataUrl = canvas.toDataURL('image/jpeg');
			    // Lanzar evento para que lo recoja el documento invocante:
			    $.event.trigger({
			        type: "imageResized",
			        url: dataUrl
			    });
			}
			image.src = base64;
		}
		, file_resize: function (file,max_size) {
			if(file.type.match(/image.*/)) {
		        var reader = new FileReader();
		        reader.onload = function (readerEvent) {
		        	var image = new Image();
					image.onload = function (imageEvent) {
					    // Resize the image
					    var canvas = document.createElement('canvas'),
					        // max_size = 544,// TODO : pull max size from a site config
					        width = image.width,
					        height = image.height;
					    if (width > height) {
					        if (width > max_size) {
								height *= max_size / width;
								width = max_size;
					        }
					    } else {
					        if (height > max_size) {
								width *= max_size / height;
								height = max_size;
					        }
					    }
					    canvas.width = width;
					    canvas.height = height;
					    canvas.getContext('2d').drawImage(image, 0, 0, width, height);
					    var dataUrl = canvas.toDataURL('image/jpeg');
					    var resizedImage = dataURLToBlob(dataUrl);
					    $.event.trigger({
					        type: "imageResized",
					        blob: resizedImage,
					        url: dataUrl
					    });
					}
					image.src = readerEvent.target.result;
		        }
		        reader.readAsDataURL(file);
			}
			return file; 
		}
	}

}();
